package com.artivisi.android.aplikasipembayaran.dto;

import java.util.List;

/**
 * Created by jimmy on 08/04/16.
 */
public class PageProduk {

    private long totalElements;
    private int totalPages;
    private boolean last;
    private int numberOfElements;
    private boolean first;
    private int size;
    private int number;
    private List<Produk> contents;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Produk> getContents() {
        return contents;
    }

    public void setContents(List<Produk> contents) {
        this.contents = contents;
    }
}
