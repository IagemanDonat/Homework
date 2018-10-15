package fileManager;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileManager {

    public void createTextFile(List<String> lines, String fileName) throws IOException {
        Path file = Paths.get(fileName);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    public void convertToPDF(Path fileTxt, Path filePdf) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePdf.toString()));
        document.open();
        BufferedReader br = new BufferedReader(new FileReader(fileTxt.toString()));
        String line;
        Paragraph p;
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            document.add(p);
        }
        document.close();
    }

    public void copyFiles2Directory(Path oldDirectory, Path newDirectory) throws IOException {
        Files.move(oldDirectory, newDirectory, REPLACE_EXISTING);
    }

    public void cascadeRemoveDirectory(Path directory) throws IOException {//NIO2 Java8
        Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void renameOnPath(Path oldDestination, Path newDestination) throws IOException {
        if (Files.isDirectory(oldDestination)) {
            Files.walk(oldDestination)
                    .sorted(Comparator.naturalOrder())
                    .forEach(path -> {
                        try {
                            Files.move(path, Paths.get(
                                    newDestination.toString() + "/" + path.getFileName()), REPLACE_EXISTING
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            Files.move(oldDestination, Paths.get(
                    newDestination.toString() + "/" + oldDestination.getFileName()), REPLACE_EXISTING
            );
        }
    }

    public void printDirectoryContent(Path directory) throws IOException {
        Files.walk(directory)
                .sorted(Comparator.naturalOrder())
                .forEach(path -> {
                    System.out.println(path.getFileName());
                });
    }
}
