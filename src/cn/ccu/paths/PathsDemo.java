package cn.ccu.paths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author huang
 * @date 2019/8/1
 */
public class PathsDemo {

    public static void main(String[] args) {
        Path path2 = Paths.get("C:/Xmp");

        URI u = URI.create("file:///C:/Xmp/dd");
        Path p = Paths.get(u);

        // FileSystems构造
        Path path3 = FileSystems.getDefault().getPath("C:/", "access.log");

        //File和Path之间的转换，File和URI之间的转换：
        File file = new File("C:/my.ini");
        Path p1 = file.toPath();
        p1.toFile();
        file.toURI();


    }

    //private static Path  path =  Paths.get("C:\\my.ini");
    //private static  Path dir = Paths.get("D:\\webworkspace");

    // 读取文件
    public void readFile(Path path){
        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String str = null;
            while((str = reader.readLine()) != null){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(Path path){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            writer.write("测试文件写操作");
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // 遍历文件夹
     public void iterateFile(Path dir){
         try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
             for(Path e : stream){
                 System.out.println(e.getFileName());
             }
         }catch(IOException e){
             e.printStackTrace();
         }
     }


     // Paths.get("C:/")   遍历文件夹2
      public void iterateFile2(Path dir){
          try (Stream<Path> stream = Files.list(dir)){
              Iterator<Path> ite = stream.iterator();
              while(ite.hasNext()){
                  Path pp = ite.next();
                  System.out.println(pp.getFileName());
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }



      // 遍历整个目录 并筛选。
    public  void  iterateDir() throws IOException {

        Path startingDir = Paths.get("F:\\upload\\images");    // F:\\upload\\images\\2\\20141206
        List<Path> result = new LinkedList<Path>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        System.out.println("result.size()=" + result.size());
        System.out.println("done.");
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path>{
        private List<Path> result;
        public FindJavaVisitor(List<Path> result){
            this.result = result;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            String filePath = file.toFile().getAbsolutePath();
            if(filePath.matches(".*_[1|2]{1}\\.(?i)(jpg|jpeg|gif|bmp|png)")){
                try {
                    Files.deleteIfExists(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.add(file.getFileName());
            } return FileVisitResult.CONTINUE;
        }
    }
}
