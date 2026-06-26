package com.test.core.model.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size
) {
}
