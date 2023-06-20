package adventofcode.aoc2022.Day16;

import java.util.ArrayList;
import java.util.Comparator;
// import java.util.HashSet;
import java.util.List;
import java.util.Map;
// import java.util.Set;
// import java.util.stream.IntStream;

/**
 * DFS
 * TODO:
 *  -> This works in principle, but needs some optimization: (current depth limit=16)
 *  -> Extract a LOT of methods for readability!!!
 *
 *  O check if Paths can be discarded based on their current pressure per minute
 *  X do not open Valve when there is a Room with more pressure to be released (double the pressure? more?)
 *  X check for circles without opening a Valve
 *  X calc the max pressure possible to be released in an ideal situation, if this is worse than the current max, discard
 */
public class DFS {

    private boolean checkedEverything;
    private int maxPressureReleased;

    private final int maxTime;
    private final List<Integer> pressurePerMinute;
    private final Path path;
    private final Map<String, Room> rooms;
    // private final Set<Path> checkedPaths;

    public DFS(Map<String, Room> rooms, int maxTime) {
        this.checkedEverything = false;
        this.maxPressureReleased = -1;
        this.maxTime = maxTime;
        this.pressurePerMinute = new ArrayList<>();
        this.path = new Path(new ArrayList<>());
        this.rooms = rooms;
        // this.checkedPaths = new HashSet<>();
    }

    public int search() {

        System.out.println("DFS.search(" + this.maxTime + ")");

        List<Integer> solutionPressure = List.of();
        Path solutionPath = new Path(List.of());

        this.path.add("AA");
        this.pressurePerMinute.add(0);
        // this.checkedPaths.add(Path.copyOf(this.path));

        // for new version without 'this.checkPaths'
        String lastRemovedRoom = null;

        while (!this.checkedEverything) {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }

            // check if the current path reached the time limit and compare it to the current maxPressure release
            if (this.path.size() >= this.maxTime) {
                int pressureReleased = this.pressurePerMinute.stream().mapToInt(Integer::valueOf).sum();
                this.maxPressureReleased = Integer.max(this.maxPressureReleased, pressureReleased);

                if (pressureReleased == this.maxPressureReleased) {
                    solutionPressure = List.copyOf(this.pressurePerMinute);
                    solutionPath = Path.copyOf(this.path);
                }

                // System.out.println(" > reached end of time. Pressure realesed: " + pressureReleased);

                // TODO: better way of removing the last step then calling this code block at two different places
                //       or at least make it a method then
                int time = this.path.size();

                // reverse the last Step
                lastRemovedRoom = this.path.removeLast();
                if (lastRemovedRoom == null) {
                    this.checkedEverything = true;
                    break;
                }
                this.pressurePerMinute.remove(time-1);
            }


            boolean a, b;
            // look for a New Move. As long as no new Step is found, remove the last one
            while (!(a=this.openValve(lastRemovedRoom)) && !(b=this.nextRoom(lastRemovedRoom))) {
                System.out.println("openValve=" + a + ", nextRoom="+b);
                int time = this.path.size();

                if (time == 0) {
                    this.checkedEverything = true;
                    break;
                }

                // reverse the last Step
                lastRemovedRoom = this.path.removeLast();
                System.out.println("lastRemovedRoom: " + lastRemovedRoom);
                if (lastRemovedRoom == null) {
                    this.checkedEverything = true;
                    break;
                }
                this.pressurePerMinute.remove(time-1);
            }
        }

        System.out.println("Solution:");
        System.out.println(solutionPath.rooms());
        System.out.println(solutionPressure);

        return this.maxPressureReleased;
    }

    /**
     * Try opening the Valve in this Room
     * @return
     */
    private boolean openValve(String lastRemovedRoom) {
        System.out.println("DFS.openValve() with Path: " + this.path + ", pressure Per Minute: " + this.pressurePerMinute + " and lastRemovedRoom: " + lastRemovedRoom);
        final int time = this.path.size();
        final String roomName = this.path.getLast();
        final Room room = this.rooms.get(roomName);

        // check if we are in a Room
        if (room == null) {
            System.out.println("There is no valid Room at the end of the Path -> false");
            return false;
        }

        // check if opening this Valve will release pressure
        if (room.valve().flowRate() <= 0) {
            System.out.println("The FlowRate is less or equal to 0 -> false");
            return false;
        }

        String valveName = roomName + "_V";
        // check if the valve of this room is already open
        if (this.path.contains(valveName)) {
            System.out.println("This Valve has already been opened -> false");
            return false;
        }

        // check if there is a Room with much higher PressureRelease per Minute adjacent to this one whos Valve has not been opened
        int maxFlowRateInNextRooms = room.tunnels().stream()
                .filter(s -> !this.path.contains(s+"_V"))
                .map(this.rooms::get)
                .map(Room::valve)
                .mapToInt(Valve::flowRate)
                .max()
                .orElse(-1);
        if (maxFlowRateInNextRooms > (2*room.valve().flowRate())) {
            System.out.println("This FlowRate is less than half the FlowRate of one of he adjacent unopened Valves -> false");
            return false;
        }


        // for version without this.checkedPaths
        if (valveName.equals(lastRemovedRoom)) {
            System.out.println("The New Valve is to be opened is the one that was removed previously -> false");
            return false;
        }

        // add the act of opening this Rooms valve to the Path
        this.path.add(valveName);

        // check if this Path was already visited
        // if (this.checkedPaths.contains(this.path)) {
        //     this.path.removeLast();
        //     return false;
        // }

        // add the new pressure released per minute to the pressurePerMinute List
        int currentPressure = this.pressurePerMinute.get(time-1);
        currentPressure += room.valve().flowRate();
        this.pressurePerMinute.add(currentPressure);

        // add the new Path to the Set of known Paths
        // this.checkedPaths.add(Path.copyOf(this.path));

        // return successful

        System.out.println("-> true");
        return true;
    }

    /**
     * Try going into a different Room
     * @return
     */
    private boolean nextRoom(String lastRemovedRoom) {
        System.out.println("DFS.nextRoom() with Path: " + this.path + ", pressure Per Minute: " + this.pressurePerMinute + " and lastRemovedRoom: " + lastRemovedRoom);
        final int time = this.path.size();

        // check if the Path has a length of at least 1
        if (time < 1) {
            System.out.println("Path is too short -> false");
            return false;
        }

        final String roomName = this.path.getLast();

        // get the current Room (trim the possible '_V' from the roomName)
        final Room currentRoom = this.rooms.get(roomName.substring(0, 2));

        // check if we are in a Room
        if (currentRoom == null) {
            System.out.println("There is no valid Room at the end of the Path -> false");
            return false;
        }

        // go into the next Room, check if the resulting path was already tried, if not add the new pressure per minute to the List
        // for (String room : currentRoom.tunnels()) {

        // for version without 'this.checkedPaths'
        for (int nextIndex = currentRoom.tunnels().indexOf(lastRemovedRoom)+1; nextIndex < currentRoom.tunnels().size(); nextIndex++) {

            String room = currentRoom.tunnels().get(nextIndex);

            // check if the path will result in a circle without having opened a Valve
            int fromIndex = this.path.lastIndexOf(room);
            if ((fromIndex != -1) && this.path.isCircle(fromIndex)) {
                continue;
            }

            // check if this will just return to the last room
            // if (tunnel.equals(this.path.getLast())) {
            //     continue;
            // }

            // add the next tunnel to the Path
            this.path.add(room);

            // check if this Path was already tried
            // if (this.checkedPaths.contains(this.path)) {
            //     this.path.removeLast();
            //     continue;
            // }

            // add the current pressure released per minute to the pressurePerMinute List
            int currentPressure = this.pressurePerMinute.get(time-1);
            this.pressurePerMinute.add(currentPressure);

            // add the new Path to the Set of known Paths
            // this.checkedPaths.add(Path.copyOf(this.path));

            // ------
            // check if this Path can only lead to worse results even in an ideal scenario
            if ((this.path.size() == (5*this.maxTime/8))) {
                int remainingTime = this.maxTime - this.path.size();
                int upToNowPressure = this.pressurePerMinute.stream().mapToInt(Integer::valueOf).sum();
                int idealFuturePressure = getRemainingRelease(remainingTime) + (this.pressurePerMinute.get(this.pressurePerMinute.size()-1)*remainingTime);

                if ((upToNowPressure + idealFuturePressure) < this.maxPressureReleased) {
                    // if (this.path.rooms().equals(List.of("AA", "DD", "DD_V", "AA", "BB", "BB_V", "AA", "II", "JJ", "JJ_V", "II", "AA", "DD", "EE", "FF"))) {
                    //     System.out.println("Throwing away path: " + this.path);
                    //     System.out.println("Because the future pressure is " + idealFuturePressure + " and the currentPressure is " + upToNowPressure + " while the maxPressure is " + this.maxPressureReleased);
                    // }
                    System.out.println("The ideal future for this Path is less than the current maxPressureReleased -> false");
                    return false;
                }
            }
            // ------

            // return successful
            System.out.println("-> true");
            return true;
        }

        // no new Room was found
        return false;
    }

    private int getRemainingRelease(int remainingTime) {

        boolean debug = false;
        // if (this.path.rooms().equals(List.of("AA", "DD", "DD_V", "AA", "BB", "BB_V", "AA", "II", "JJ", "JJ_V", "II", "AA", "DD", "EE", "FF"))) {
        //     debug = true;
        // }

        if (debug)
            System.out.println("DFS.getRemainingRelease()");

        List<String> openValves = this.path.rooms().stream()
                .filter(str -> str.endsWith("_V"))
                .map(str -> str.substring(0, 2))
                .toList();

        if (debug)
            System.out.println("openVavles: " + openValves);

        List<Integer> closedValvesFlowRate = this.rooms.keySet().stream()
                .filter(str -> !openValves.contains(str))
                .map(this.rooms::get)
                .map(r -> r.valve().flowRate())
                .sorted(Comparator.reverseOrder())
                .limit(remainingTime)
                .toList();

        if (debug)
            System.out.println("closedValves: " + closedValvesFlowRate);

        List<Integer> sum = new ArrayList<>();
        sum.add(0);
        for (int i = 0; i < remainingTime/2; i++) {
            int oldPressure = sum.get(sum.size()-1);
            if (i < closedValvesFlowRate.size()) {
                int newPressure = closedValvesFlowRate.get(i);
                oldPressure += newPressure;
            }
            sum.add(oldPressure);
            sum.add(oldPressure);
        }

        if (debug)
            System.out.println("Ideal Future Pressure: " + sum);

        return sum.stream().mapToInt(Integer::valueOf).sum();
    }

}
