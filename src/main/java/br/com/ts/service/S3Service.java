package br.com.ts.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.ts.service.exception.FileException;

@Service 
@Transactional(readOnly = false)
public class S3Service {

	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(String nomeArquivoFinal, MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			
			return uploadFile(is, nomeArquivoFinal, contentType);
		}catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	private URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			s3client.putObject(bucketName, fileName, is, meta);
			
			return s3client.getUrl(bucketName, fileName).toURI();
		
		}catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI.");
		}
	}
	
}
