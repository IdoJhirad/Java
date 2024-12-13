package watchservice;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DirMonitor {

    public static void main(String[] args){
        boolean watchServiceFlag = true;
        File logger = new File("/home/ido/Desktop/Dir_Monitor");
        try {
            logger.createNewFile();
            System.setOut(new PrintStream(logger));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //create the watch process
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            //create the path variable that will register as watchable object
            Path path = Paths.get("/home/ido/git");
            path.register(
                    /* ENTRY_CREATE triger when file is renamed or new file created
                    * ENTRY_MODIFY trigerd when an existing file  in the watch dir is modified
                    * ENTRY_DELETE trigerd when entry is deleterd moves or rename */
                    watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE );
            WatchKey key;
            while (watchServiceFlag) {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()){
                    String timestamp = dtf.format(LocalDateTime.now());
                    System.out.println(timestamp+" Event :"+event.kind() +" affected "+event.context());
                }
                key.reset();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
