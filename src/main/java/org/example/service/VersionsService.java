package org.example.service;
import org.example.model.ConsentPubl;
import org.example.model.Image;
import org.example.model.Photosession;
import org.example.model.Versions;
import org.example.repository.ConsentPublRepository;
import org.example.repository.VersionsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VersionsService implements IVersionsService
{
    @Autowired
    private final VersionsRepository versionsRepository;
    @Autowired
    private final ConsentPublRepository consentPublRepository;

    public VersionsService(VersionsRepository versionsRepository, ConsentPublRepository consentPublRepository) {
        this.versionsRepository = versionsRepository;
        this.consentPublRepository = consentPublRepository;
    }

    public ResponseEntity<List<Long>> getAllVersions(String login)
    {
        List<Versions> versions = versionsRepository.findAll();
        List<Long> id = new ArrayList<>();
        for(int i = 0; i < versions.size(); i++) {
            if(Objects.equals(versions.get(i).getImageOrig().getPhotosession().getAuthor().getLoginUsers(), login) && (Objects.equals(versions.get(i).getConsent_publ().getNameConsentPubl(), "На согласовании")) && (Objects.equals(versions.get(i).getConsent_publ_client().getNameConsentPubl(), "Согласована"))){
                id.add(versions.get(i).getIdVersions());
            }
        }

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    public ResponseEntity<String> updateVdersionClient(String idVersion, String name)
    {
        HttpHeaders heards = new HttpHeaders();
        Versions versions1 = versionsRepository.findById(Long.parseLong(idVersion)).get();
        if (versions1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ConsentPubl consentPubl = consentPublRepository.findConsentPublByNameConsentPubl(name);
        versions1.setConsent_publ_client(consentPubl);
        this.versionsRepository.save(versions1);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strup", "Данные успешно обновлены");
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, heards, HttpStatus.OK);
    }

    public ResponseEntity<String> updateCancel(Photosession photosession)
    {
        HttpHeaders heards = new HttpHeaders();
        System.out.println("JJJJJJJJJJJJJ"+photosession.getIdPhotosession());
        List<Versions> versions1 = versionsRepository.findAll();
        ConsentPubl consentPubl = consentPublRepository.findConsentPublByNameConsentPubl("Не выбрана для согласования");
        if (versions1 == null) {
            System.out.println("JJJJJJJJJJJJJ"+photosession.getIdPhotosession());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for(int i = 0; i < versions1.size(); i++)
        {
            if(Objects.equals(versions1.get(i).getImageOrig().getPhotosession().getIdPhotosession(), photosession.getIdPhotosession()))
            {
                versions1.get(i).setConsent_publ_client(consentPubl);
            }
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strup", "Данные успешно обновлены");
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, heards, HttpStatus.OK);
    }

    public ResponseEntity<String> updateVersionAuthor(String idVersion, String name)
    {
        HttpHeaders heards = new HttpHeaders();
        Versions versions1 = versionsRepository.findById(Long.parseLong(idVersion)).get();
        if (versions1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("FGHJ" +name);
        ConsentPubl consentPubl = consentPublRepository.findConsentPublByNameConsentPubl(name);
        System.out.println("FGHJ" +consentPubl.getNameConsentPubl());
        System.out.println("FGHJ" +versions1.getIdVersions());
        versions1.setConsent_publ(consentPubl);
        this.versionsRepository.save(versions1);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strup", "Данные успешно обновлены");
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, heards, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getNameImage(Photosession photosession)
    {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> imagesName = new ArrayList<>();
        System.out.println("ЗАШЛО В СОЗДАНИЕ СОДЕРЖАНИЯ"+photosession);
        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();

        for(int i = 0; i < versionsList.size(); i++)
        {
            if(Objects.equals(versionsList.get(i).getImageOrig().getPhotosession().getIdPhotosession(), photosession.getIdPhotosession()))
            {
                System.out.println("ЗАШЛО В СОЗДАНИЕ СОДЕРЖАНИЯ"+versionsList.get(i).getImageOrig().getName());
                imagesName.add(versionsList.get(i).getImageOrig().getName());
            }

        }
        return new ResponseEntity<>(imagesName, headers, HttpStatus.OK);

    }

    public ResponseEntity<byte[]> getImagesLink(Photosession photosession) {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        if (versionsList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> imagesLink = new ArrayList<>();

        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();


        for(int i = 0; i < versionsList.size(); i++)
        {
            System.out.println(versionsList.get(i).getImage().getName());
            System.out.println(versionsList.get(i).getImage().getIdImage());
            System.out.println(versionsList.size());
            if(Objects.equals(versionsList.get(i).getImage().getPhotosession().getIdPhotosession(), photosession.getIdPhotosession()))
            {
                System.out.println("fghjk   "+versionsList.get(i).getImage().getLink());
                imagesLink.add(versionsList.get(i).getImage().getLink());
                System.out.println(versionsList.get(i).getImage().getLink() + " HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
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

    public ResponseEntity<String> createVersion(Versions versions, String name) {
        HttpHeaders headers = new HttpHeaders();
        if (versions == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRR" + name);
        ConsentPubl consentPubl = consentPublRepository.findConsentPublByNameConsentPubl(name);

        versions.setConsent_publ(consentPubl);
        versions.setConsent_publ_client(consentPubl);
        Long id = this.versionsRepository.save(versions).getIdVersions();
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRR" + id);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strid", id);
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    public ResponseEntity<List<Image>> getImagesForClient(String name) {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        List<ConsentPubl> consentPublList = consentPublRepository.findAll();
        ConsentPubl consentPubl = null;
        ConsentPubl consentPublClient = null;
        for(int i = 0; i < consentPublList.size(); i++)
        {
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "На согласовании")){
                consentPubl = consentPublList.get(i);
            }
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "Согласована"))
            {
                consentPublClient = consentPublList.get(i);
            }
        }
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < versionsList.size(); i++)
        {

            if ((versionsList.get(i).getConsent_publ() == consentPubl) && (versionsList.get(i).getConsent_publ_client() == consentPublClient) &&(Objects.equals(versionsList.get(i).getImageOrig().getPhotosession().getClient().getLoginUsers(), name))) {
                images.add(versionsList.get(i).getImage());
            }
        }

        return new ResponseEntity<>(images, headers, HttpStatus.OK);

    }

    public ResponseEntity<List<Image>> getImages() {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        List<ConsentPubl> consentPublList = consentPublRepository.findAll();
        ConsentPubl consentPubl = null;
        ConsentPubl consentPublClient = null;
        for(int i = 0; i < consentPublList.size(); i++)
        {
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "На согласовании")){
                consentPubl = consentPublList.get(i);
            }
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "Согласована"))
            {
                consentPublClient = consentPublList.get(i);
            }
        }
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < versionsList.size(); i++)
        {

            if ((versionsList.get(i).getConsent_publ() == consentPubl) && (versionsList.get(i).getConsent_publ_client() == consentPublClient)) {
                images.add(versionsList.get(i).getImage());
            }

        }

        return new ResponseEntity<>(images, headers, HttpStatus.OK);

    }

    public ResponseEntity<byte[]> getImagesPublicClient(String name) {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        List<ConsentPubl> consentPublList = consentPublRepository.findAll();
        ConsentPubl consentPubl = null;
        ConsentPubl consentPublClient = null;
        for(int i = 0; i < consentPublList.size(); i++)
        {
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "На согласовании")){
                consentPubl = consentPublList.get(i);
            }
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "Согласована"))
            {
                consentPublClient = consentPublList.get(i);
            }
        }
        List<String> imagesLink = new ArrayList<>();
        for (int i = 0; i < versionsList.size(); i++)
        {

            if ((versionsList.get(i).getConsent_publ() == consentPubl) && (versionsList.get(i).getConsent_publ_client() == consentPublClient) && (Objects.equals(versionsList.get(i).getImage().getPhotosession().getClient().getLoginUsers(), name))) {
                imagesLink.add(versionsList.get(i).getImage().getLink());
            }

        }
        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();
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

    public ResponseEntity<byte[]> getImagesPublicPhotograph(String name) {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        List<ConsentPubl> consentPublList = consentPublRepository.findAll();
        ConsentPubl consentPubl = null;
        ConsentPubl consentPublClient = null;
        for(int i = 0; i < consentPublList.size(); i++)
        {
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "На согласовании")){
                consentPubl = consentPublList.get(i);
            }
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "Согласована"))
            {
                consentPublClient = consentPublList.get(i);
            }
        }
        List<String> imagesLink = new ArrayList<>();
        for (int i = 0; i < versionsList.size(); i++)
        {

            if ((versionsList.get(i).getConsent_publ() == consentPubl) && (versionsList.get(i).getConsent_publ_client() == consentPublClient) && (Objects.equals(versionsList.get(i).getImage().getPhotosession().getAuthor().getLoginUsers(), name))) {
                imagesLink.add(versionsList.get(i).getImage().getLink());
            }

        }

        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();
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

    public ResponseEntity<byte[]> getImagesLinkProcess() {
        HttpHeaders headers = new HttpHeaders();
        List<Versions> versionsList = versionsRepository.findAll();
        List<ConsentPubl> consentPublList = consentPublRepository.findAll();
        ConsentPubl consentPubl = null;
        ConsentPubl consentPublClient = null;
        for(int i = 0; i < consentPublList.size(); i++)
        {
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "На согласовании")){
                consentPubl = consentPublList.get(i);
            }
            if(Objects.equals(consentPublList.get(i).getNameConsentPubl(), "Согласована"))
            {
                consentPublClient = consentPublList.get(i);
            }
        }
        List<String> imagesLink = new ArrayList<>();
        for (int i = 0; i < versionsList.size(); i++)
        {

            if ((versionsList.get(i).getConsent_publ() == consentPubl) && (versionsList.get(i).getConsent_publ_client() == consentPublClient)) {
                imagesLink.add(versionsList.get(i).getImage().getLink());
            }

        }

        byte[] bytes = null;
        List<byte[]> images = new ArrayList<>();
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

    public ResponseEntity<String> getVersion() {
        List<Versions> versionsList = versionsRepository.findAll();
        if (versionsList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Versions> versionsRes = new ArrayList<>();
        List<String> vers = new ArrayList<>();
        List<Long> id = new ArrayList<>();
        for(int i = 0; i<versionsList.size(); i++)
        {
            if(Objects.equals(versionsList.get(i).getConsent_publ().getNameConsentPubl(), "На согласовании") && Objects.equals(versionsList.get(i).getConsent_publ_client().getNameConsentPubl(), "Не выбрана для согласования"))
            {
                versionsRes.add(versionsList.get(i));
                id.add(versionsList.get(i).getIdVersions());
            }
        }
        for(int i = 0; i<versionsRes.size();i++)
        {
            vers.add(versionsRes.get(i).getImage().getName());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("versname", vers);
        jsonObject.put("versid", id);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);


    }

}
