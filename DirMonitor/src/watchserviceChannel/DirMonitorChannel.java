package watchserviceChannel;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DirMonitorChannel {
    public static void main(String[] args) {
        boolean watchServiceFlag = true;
        Path logFilePath = Paths.get("/home/ido/Desktop/Dir_Monitor2");

        try {
            if (!Files.exists(logFilePath)) {
                 Files.createFile(logFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        try (WatchService watchService = FileSystems.getDefault().newWatchService();

             FileChannel fileChannel = FileChannel.open(logFilePath,  StandardOpenOption.APPEND,StandardOpenOption.APPEND)) {
            Path path = Paths.get("/home/ido/git");
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE
            );
            WatchKey key;
            while (watchServiceFlag) {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    String timestamp = dtf.format(LocalDateTime.now());
                    String logMessage = "[" + timestamp + "] Event: " + event.kind() + " affected " + event.context() + "\n";
                    ByteBuffer buffer = ByteBuffer.wrap(logMessage.getBytes());
                    fileChannel.write(buffer);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}