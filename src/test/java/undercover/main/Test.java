package undercover.main;

public class Test {

    public static void main(String[] args) {
        while (true){

        int roomKeyInt = (int) ((Math.random() * 9 + 1) * 100000);
        System.out.println(roomKeyInt);
        String formatKey = "%06d";
        String roomKey = String.format(formatKey, roomKeyInt);
        System.out.println(roomKey);
        }
    }
}