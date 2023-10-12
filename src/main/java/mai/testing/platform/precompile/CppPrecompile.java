package mai.testing.platform.precompile;

import lombok.NonNull;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CppPrecompile implements BasePrecompile{
    @Override
    public void execute(String path) {
        String lib_sources = findCppFiles(path + "/src");
        String test_sources = findCppFiles(path + "/test");
        System.out.println(lib_sources);
        System.out.println(test_sources);

        makeCmakeList(path, lib_sources, test_sources);
        copyScript(path);
    }


    private String findCppFiles(@NonNull String directoryPath) {
        StringBuilder builder = new StringBuilder();
        List<String> cppFilePaths = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Указанная директория не существует или не является директорией.");
            return " ";
        }

        findCppFilesRecursively(directory, builder);

        return builder.toString();
    }

    private void findCppFilesRecursively(@NonNull File directory,@NonNull StringBuilder cppFilePaths) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Рекурсивный вызов для поддиректорий
                findCppFilesRecursively(file, cppFilePaths);
            } else if (file.isFile() && (file.getName().endsWith(".cpp") || file.getName().endsWith(".c"))) {
                cppFilePaths.append(file.getAbsolutePath());
            }
        }
    }

    private void makeCmakeList(String path, String libs, String tests) {
        String inputFile = "./src/main/resources/CMakeList.txt";
        String outputFile = path + "/CMakeLists.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                // Заменяем "{source_files}" на "src/main.cpp" и записываем в выходной файл
                line = line.replace("{libs_sources}", libs);
                line = line.replace("{test_sources}", tests);
                writer.write(line);
                writer.newLine();
            }

            // Закрываем файлы
            reader.close();
            writer.close();

            System.out.println("Замена завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeBuildDir(String path) {
        File folder = new File(path + "/build");
        if (!folder.exists()) {
            boolean success = folder.mkdirs();
            if (!success) {
                //TODO log error
            }
        }
    }

    private void copyScript(String path) {
        File srcFile = new File("./src/main/resources/build_and_run.sh");
        File destFile = new File(path + "/cpp/build_and_run.sh");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
