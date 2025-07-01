package entity;

import java.util.Arrays;

public class CategoryManagement implements ICRUD {
	private Category[] categories;
	private int currentIndex;

	public CategoryManagement (int capacity) {
		categories = new Category[capacity];
		currentIndex = 0;
	}

	public Category[] getCategories () {
		return categories;
	}

	@Override
	public Category[] findAll () {
		Category[] result = new Category[currentIndex];
		System.arraycopy(categories, 0, result, 0, currentIndex);
		return result;
	}

	@Override
	public void addCategory (Category category) {
		categories[currentIndex] = category;
		currentIndex++;
		System.out.println("Đã thêm thành công!");
	}

	@Override
	public void updateCategory (Category category) {
		int indexForUpdate = findCategoryIndexById(category.getId());
		if (indexForUpdate == -1) {
			System.err.println("Không tồn tại category này");
		} else {
			categories[indexForUpdate].setName(category.getName());
			categories[indexForUpdate].setDescription(category.getDescription());
			System.out.println("Đã cập nhật thành công!");
		}
	}

	@Override
	public void deleteById (int id) {
		int indexForDelete = findCategoryIndexById(id);
		if (indexForDelete == -1) {
			System.err.println("Không tồn tại category này");
		} else {
			for (int i = indexForDelete; i < currentIndex - 1; i++) {
				categories[i] = categories[i + 1];
			}
			categories[currentIndex - 1] = null;
			currentIndex--;
			System.out.println("Đã xóa thành công!");
		}
	}

	public int findCategoryIndexById (int id) {
		int index = -1;
		for (int i = 0; i < currentIndex; i++) {
			if (categories[i].getId() == id) {
				index = i;
				break;
			}
		}
		return index;
	}
}
