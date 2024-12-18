package in.shakthi.services;

import java.util.List;

import in.shakthi.entity.Document;

public interface IDocumentService {
	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);
}
