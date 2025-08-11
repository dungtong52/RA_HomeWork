package ra.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.Category_EN;
import ra.edu.model.Category_VI;
import ra.edu.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category_EN> getCategoriesEN() {
        return categoryRepository.findAllEN();
    }

    public List<Category_VI> getCategoriesVI() {
        return categoryRepository.getAllVI();
    }

    public boolean addCategory(Category_VI categoryVi,Category_EN category) {
        return categoryRepository.addCategory(categoryVi,category);
    }
}
