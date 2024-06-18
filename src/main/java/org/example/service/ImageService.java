package org.example.service;
import net.coobird.thumbnailator.Thumbnails;
import org.example.model.Image;
import org.example.model.Photosession;
import org.example.repository.ImageRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService implements IImageService {
    @Autowired
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<String> getAllImage() {
        List<Image> images = this.imageRepository.findAll();
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image", images);
        String string = jsonObject.toString();
        System.out.println("Зашло в меню");
        System.out.println(string);
        return new ResponseEntity<>(string, HttpStatus.OK);

    }

    public ResponseEntity<List<Image>> getImagePhot(Photosession photosession) {
        HttpHeaders headers = new HttpHeaders();
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        String outputDirectoryPath = "C:\\Users\\Юлия\\Downloads\\Diplom\\Images\\";

        List<Image> imageList = imageRepository.findImageByPhotosession(photosession);
        List<Image> imageList1 = new ArrayList<>();
        for(int i=0; i < imageList.size(); i++)
        {
            if(imageList.get(i).getLinkorig().startsWith(outputDirectoryPath))
            {
                imageList1.add(imageList.get(i));
            }
        }
        return new ResponseEntity<>(imageList1, headers, HttpStatus.OK);
    }

    public ResponseEntity<Image> updateImage(Long id, Image image) {
        HttpHeaders heards = new HttpHeaders();
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Image image1 = imageRepository.findById(id).get();
        image1.setProcess(image.getProcess());
        this.imageRepository.save(image);
        return new ResponseEntity<>(image, heards, HttpStatus.OK);
    }

    public ResponseEntity<Image> createImage(Image image) {
        HttpHeaders headers = new HttpHeaders();
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("ЗАШЛО В СОЗДАНИЕ ИЗОБРАЖЕНИЯ" + image.getLink());

        this.imageRepository.save(image);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    public ResponseEntity<org.springframework.core.io.Resource> downloadPhotoProcess(String name) {
        String directory = "C:\\Users\\Юлия\\Downloads\\Diplom\\ImagesProcessing";
        Path photoPath = Paths.get(directory, name);
        Resource resource = new FileSystemResource(photoPath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public ResponseEntity<org.springframework.core.io.Resource> downloadPhoto(String name) {
        String directory = "C:\\Users\\Юлия\\Downloads\\Diplom\\Images";
        Path photoPath = Paths.get(directory, name);
        Resource resource = new FileSystemResource(photoPath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public ResponseEntity<String> createLinkProcessing(MultipartFile file, String name) {
        HttpHeaders headers = new HttpHeaders();
        String str = "";
        String str2 = "";
        Image im = null;
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = file.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(bis);
            String outputDirectoryPath = "C:\\Users\\Юлия\\Downloads\\Diplom\\Images\\";
            String preOutputDirectoryPath = "C:\\Users\\Юлия\\Downloads\\Diplom\\PreImages\\";
            File outputFile = new File(outputDirectoryPath + name);
            File preOutputFile = new File(preOutputDirectoryPath + name);
            if (outputFile.exists() && preOutputFile.exists()) {
                List<Image> image1 = imageRepository.findAll();

                for(int i = 0; i < image1.size(); i++)
                {
                    if(Objects.equals(image1.get(i).getLinkorig(), outputDirectoryPath + name))
                    {
                        im=image1.get(i);
                    }
                }
                str = "C:\\Users\\Юлия\\Downloads\\Diplom\\ImagesProcessing\\" + name;
                str2 = "C:\\Users\\Юлия\\Downloads\\Diplom\\PreImagesProcessing\\" + name;
                File output = new File(str);
                ImageIO.write(image, "jpg", output);
                File preoutput = new File(str2);
                Thumbnails.of(output)
                        .scale(0.25)
                        .outputQuality(0.5)
                        .toFile(preoutput);

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("link", str2);
        jsonObject.put("linkorig", str);
        if(im!=null)
            jsonObject.put("image", im.toString());
        String string = jsonObject.toString();
        System.out.println("ССЫЛКА "+string);
        return new ResponseEntity<>(string, headers, HttpStatus.OK);
    }

    public ResponseEntity<String> createLink(MultipartFile file, String name) {
        HttpHeaders headers = new HttpHeaders();
        String str = "";
        String str2 = "";
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = file.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(bis);
            str = "C:\\Users\\Юлия\\Downloads\\Diplom\\Images\\" + name;
            str2 = "C:\\Users\\Юлия\\Downloads\\Diplom\\PreImages\\" + name;
            File output = new File(str);
            ImageIO.write(image, "jpg", output);
            File preoutput = new File(str2);
            Thumbnails.of(output)
                    .scale(0.25)
                    .outputQuality(0.5)
                    .toFile(preoutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("link", str2);
        jsonObject.put("linkorig", str);
        String string = jsonObject.toString();
        System.out.println("ССЫЛКА "+string);
        return new ResponseEntity<>(string, headers, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getNameImage(Photosession photosession) {
        HttpHeaders headers = new HttpHeaders();
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<String> imagesName = new ArrayList<>();

        List<Image> imagePhotosessionList = imageRepository.findImageByPhotosession(photosession);
        for(int i = 0; i < imagePhotosessionList.size(); i++)
        {
            String image = imagePhotosessionList.get(i).getName();
            if (!imagesName.contains(image)) {
                imagesName.add(image);
            }
        }
        return new ResponseEntity<>(imagesName, headers, HttpStatus.OK);

    }

    public ResponseEntity<Image> deleteImagePhotosession(Image imagePhotosession) {
        HttpHeaders headers = new HttpHeaders();
        if (imagePhotosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.imageRepository.deleteById(imagePhotosession.getIdImage());
        return new ResponseEntity<>(imagePhotosession, headers, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> getImagesLinkProcess(List<String> links) {
        HttpHeaders headers = new HttpHeaders();
        if (links == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();
        byte[] serial = null;
        try {
            for (int i = 0; i < links.size(); i++) {
                Path path = Paths.get(links.get(i));
                bytes = Files.readAllBytes(path);
                images.add(bytes);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(images);
            objectOutputStream.flush();
            serial = byteArrayOutputStream.toByteArray();
        }catch (IOException e){}

        return new ResponseEntity<>(serial, headers, HttpStatus.OK);

    }


    public ResponseEntity<byte[]> getImagesLink(Photosession photosession) {
        HttpHeaders headers = new HttpHeaders();
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> imagesLink = new ArrayList<>();
        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();
        List<Image> imagePhotosessionList = imageRepository.findImageByPhotosession(photosession);
        String outputDirectoryPath = "C:\\Users\\Юлия\\Downloads\\Diplom\\Images\\";

        for(int i = 0; i < imagePhotosessionList.size(); i++)
        {
            if(imagePhotosessionList.get(i).getLinkorig().startsWith(outputDirectoryPath)) {
                imagesLink.add(imagePhotosessionList.get(i).getLink());
            }
        }
        byte[] serial = null;

        try {
            for (int i = 0; i < imagesLink.size(); i++) {
                Path path = Paths.get(imagesLink.get(i));
                bytes = Files.readAllBytes(path);
                images.add(bytes);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(images);
            objectOutputStream.flush();
            serial = byteArrayOutputStream.toByteArray();
        }catch (IOException e){}

        return new ResponseEntity<>(serial, headers, HttpStatus.OK);

    }


    public ResponseEntity<Image> getImage(Long idImage) {
        if (idImage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Image image = this.imageRepository.findById(idImage).get();
        if (idImage == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
