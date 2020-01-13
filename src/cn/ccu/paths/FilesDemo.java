package cn.ccu.paths;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author huang
 * @date 2019/8/1
 */
public class FilesDemo {

   // 从文件复制到文件：Files.copy(Path source, Path target, CopyOption options);

    //从输入流复制到文件：Files.copy(InputStream in, Path target, CopyOption options);

    //从文件复制到输出流：Files.copy(Path source, OutputStream out);

    public static void main(String[] args) throws Exception {

          //注意创建目录和文件Files.createDirectories 和 Files.createFile不能混用，
         //  必须先有目录，才能在目录中创建文件。
        try {
            Files.createDirectories(Paths.get("C://Test"));
            if(!Files.exists(Paths.get("C://Test")))
                Files.createFile(Paths.get("C://Test/test.txt"));
//            Files.createDirectories(Paths.get("C://Test/test2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 读取文件属性
        Path zip = Paths.get("..");
        System.out.println(Files.getLastModifiedTime(zip));
        System.out.println(Files.size(zip));
        System.out.println(Files.isSymbolicLink(zip));
        System.out.println(Files.isDirectory(zip));
        System.out.println(Files.readAttributes(zip, "*"));




        // 读取和设置文件权限：
        Path profile = Paths.get("/home/digdeep/.profile");
        PosixFileAttributes attrs = Files.readAttributes(profile, PosixFileAttributes.class);// 读取文件的权限
        Set<PosixFilePermission> posixPermissions = attrs.permissions();
        posixPermissions.clear();
        String owner = attrs.owner().getName();
        String perms = PosixFilePermissions.toString(posixPermissions);
        System.out.format("%s %s%n", owner, perms);

        posixPermissions.add(PosixFilePermission.OWNER_READ);
        posixPermissions.add(PosixFilePermission.GROUP_READ);
        posixPermissions.add(PosixFilePermission.OTHERS_READ);
        posixPermissions.add(PosixFilePermission.OWNER_WRITE);

        Files.setPosixFilePermissions(profile, posixPermissions);    // 设置文件的权限


        // 读文件
        List<String> collect = Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).collect(Collectors.toList());
    }

}
