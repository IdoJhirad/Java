//
//public class DirMonitor {
//    public static void main(String[] args){
//        boolean watchServiceFlag = true;
//        //Path logger = Paths.get("/home/ido/Desktop/Dir_Monitor3");
//        
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        //create the watch process
//        try (/**/WatchService watchService = FileSystems.getDefault().newWatchService()/**/;/* BufferedWriter bufferedWriter = Files.newBufferedWriter(logger, StandardOpenOption.APPEND)*/) {
//
//            Path path = Paths.get("/home/ido/git");
//           /**/
//            path.register( watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE );
//            WatchKey key;
//
//            while (watchServiceFlag) {
//                key = watchService.take();
//                for (WatchEvent<?> event : key.pollEvents()){
////                    String timestamp = dtf.format(LocalDateTime.now());
////                    String log = timestamp+" Event :"+event.kind() +" affected "+event.context() +"\n";
////                    bufferedWriter.write(log);
////                    bufferedWriter.flush();
//                }
//                key.reset();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}