package info.aservices.ftk6.dc.webhelpers;

import java.util.ArrayList;
import java.util.Collection;

public class PaginationHelper {
    static final int MAX_PAGES = 15;
    public class PaginationEntry {
        public final String href;
        public final String label;
        public final int number;

        public PaginationEntry(String href, String label, int number) {
            this.href = href;
            this.label = label;
            this.number = number;
        }
    }

    public Collection<PaginationEntry> getPagination(int currentPage, int totalPages) {
        Collection<PaginationEntry> result = new ArrayList<>();
        int firstPage, lastPage;

        //fixme: избавиться от магических чисел
        if (totalPages > MAX_PAGES) {
            firstPage = currentPage - 7;
            if (firstPage < 1) {
                firstPage = 1;
            }
            lastPage = firstPage + 14;
            if (lastPage > totalPages) {
                lastPage = totalPages;
            }
        } else {
            firstPage = 1;
            lastPage = totalPages;
        }

        for (int i = firstPage; i <= lastPage; i++) {
            result.add(new PaginationEntry("?page=" + i, "" + i, i));
        }

        return result;
    }
}
