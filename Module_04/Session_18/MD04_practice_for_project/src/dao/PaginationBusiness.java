package dao;

import entity.PaginationResult;

public interface PaginationBusiness<T> {
    PaginationResult<T> getPaginationData(String key, int size, int currentPage);
}
