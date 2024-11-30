package org.personsal.mybatis.common.page;

import lombok.Data;
import org.personsal.mybatis.common.enums.SortOrder;
import org.personsal.mybatis.common.request.Request;

/** created by: maharjananil created on: 11/15/2024 */
@Data
public class SearchRequest implements Request {
    private int pageNumber;
    private int pageSize;
    private String sortField;
    private SortOrder sortOrder;
    private int bookId;
}
