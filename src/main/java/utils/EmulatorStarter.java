package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmulatorStarter {


    public static void startEmulator(String emulatorName) {
        try {
            if (isEmulatorRunning()) {
                System.out.println("✅ Emulator already running.");
                return;
            }

            System.out.println("🔄 Starting emulator: " + emulatorName);
            ProcessBuilder builder = new ProcessBuilder("emulator", "-avd", emulatorName);
            builder.redirectErrorStream(true);
            builder.start();

            // Wait for emulator to boot completely
            Thread.sleep(20000); // Or use adb wait-for-device + check boot animation status

            System.out.println("✅ Emulator started successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmulatorRunning() {
        try {
            // Create a process to run the command: adb devices
            ProcessBuilder builder = new ProcessBuilder("adb", "devices");

            // Start the process
            Process process = builder.start();

            // Set up a BufferedReader to read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            // Read each line of output from the adb command
            while ((line = reader.readLine()) != null) {
                // Check if the line contains "emulator-"
                // which is the prefix used by Android for emulators (e.g., emulator-5554)
                if (line.contains("emulator-")) {
                    // If an emulator is found, return true
                    return true;
                }
            }
        } catch (Exception e) {
            // If an error occurs (e.g., adb not found), print the stack trace
            e.printStackTrace();
        }

        // If no emulator was found or an exception occurred, return false
        return false;
    }


}
