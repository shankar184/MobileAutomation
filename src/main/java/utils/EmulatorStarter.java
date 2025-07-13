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
            ProcessBuilder builder = new ProcessBuilder("adb", "devices");
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("emulator-")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
