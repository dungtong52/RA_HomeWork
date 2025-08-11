package com.ra.service;

import com.ra.model.PaginationResult;

public interface PaginationService<T> {
    PaginationResult<T> getPaginationData(T item, int size, int current);
}
