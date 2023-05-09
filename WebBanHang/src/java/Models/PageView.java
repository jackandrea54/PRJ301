/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author LENOVO
 */
public class PageView {

    public PageView() {
    }
    private int n;
    private int nrpp;
    private int index;
    private int totalPage;
    private int begin;
    private int end;
    private int pageStart;
    private int pageEnd;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public PageView(int n, int nrpp, int index) {
        this.n = n;
        this.nrpp = nrpp;
        this.index = index;
    }

    public void calculate() {
        totalPage = (n+nrpp-1)/nrpp;
        index = index<0?0:index;
        index = index>=totalPage?totalPage-1:index;
        begin = (index-1)*nrpp;
        begin = index*nrpp;//index bat dau tu 0
        end = begin + nrpp-1;//ket thuc tai end
        begin = begin>0?begin:0;
        end = end<n?end:n-1;
        pageStart = index-2;
        pageStart = pageStart<0?0:pageStart;
        pageEnd = index +2;
        pageEnd = pageEnd>=totalPage?totalPage-1:pageEnd;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
