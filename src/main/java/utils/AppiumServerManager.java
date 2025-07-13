package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppiumServerManager {

    private static final int PORT = 4723;

    public static void startServer() {
        try {
            if (isPortInUse(PORT)) {
                System.out.println("⚠️ Port " + PORT + " is already in use. Killing process...");
                killPort(PORT);
                Thread.sleep(2000);
            }

            System.out.println("🚀 Starting Appium server on port " + PORT);
            ProcessBuilder builder = new ProcessBuilder("appium", "-a", "127.0.0.1", "-p", String.valueOf(PORT));
            builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            builder.redirectError(ProcessBuilder.Redirect.INHERIT);
            builder.start();

            // Wait for server to boot
            Thread.sleep(5000);

            if (!isPortInUse(PORT)) {
                throw new RuntimeException("❌ Appium server failed to start on port " + PORT);
            }

            System.out.println("✅ Appium server is running on port " + PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPortInUse(int port) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("lsof", "-i", ":" + port);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine() != null;
    }

    private static void killPort(int port) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("lsof", "-ti", ":" + port);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            String pid = line.trim();
            if (!pid.isEmpty()) {
                new ProcessBuilder("kill", "-9", pid).start().waitFor();
                System.out.println("🔪 Killed process on port " + port + " (PID: " + pid + ")");
            }
        }
    }
//stop the server
    public static void stopServer() {
        try {
            System.out.println("Stopping Appium server on port " + PORT);
            killPort(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
