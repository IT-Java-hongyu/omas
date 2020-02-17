package com.fosustu.omas.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.fosustu.omas.mapper.TagMapper;
import com.fosustu.omas.pojo.Tag;
import com.fosustu.omas.pojo.TagList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.ApartmentMapper;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.service.ApartmentService;


@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentMapper apartmentMapper;

    @Autowired
    private TagMapper tagMapper;

    /**
     * 部门分页
     */
    @Override
    public List<Apartment> listByPage(Pagination agination) {
        return apartmentMapper.listByPage(agination);
    }

    /**
     * 通过id获取部门
     */
    @Override
    public Apartment getApartementById(String id) {
        return apartmentMapper.getApartementById(id);
    }

    /**
     * 通过id更新部门
     */
    @Override
    public void updateApartmentById(Apartment apartment) {
        apartmentMapper.updateApartmentById(apartment);

    }

    /*
     * 通过id删除部门
     * (non-Javadoc)
     * @see com.fosustu.omas.service.ApartmentService#deleteApartmentById(com.fosustu.omas.pojo.Apartment)
     */
    @Override
    public void deleteApartmentById(Apartment apartment) {
        apartmentMapper.deleteApartmentById(apartment);
    }

    /*新增部门
     * (non-Javadoc)
     * @see com.fosustu.omas.service.ApartmentService#addApartment(com.fosustu.omas.pojo.Apartment)
     */
    @Override
    public void addApartment(Apartment apartment) {
        apartmentMapper.addApartment(apartment);
    }

    /*
     *   获取部门总数
     * (non-Javadoc)
     * @see com.fosustu.omas.service.ApartmentService#getCount()
     */
    @Override
    public int getCount() {

        return apartmentMapper.getCount();
    }

    /**
     * 获取所有部门
     */
    @Override
    public List<Apartment> getAllApartement() {
        // TODO Auto-generated method stub
        return apartmentMapper.getAllApartment();
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<Apartment> getRankedApartments(TagList tagList) {
        List<Apartment> apartments = apartmentMapper.getAllApartment();
        float[][] scores = new float[apartments.size()][5];
        for (Tag t: tagList.getTags()) {
            String tagName = t.getTagName();
            for (int i = 0; i < apartments.size(); i++) {
                if (apartments.get(i).getTag0().equals(tagName)) {
                    scores[i][0] += 1;
                } else if (apartments.get(i).getTag1().equals(tagName)) {
                    scores[i][1] += 1;
                } else if (apartments.get(i).getTag2().equals(tagName)) {
                    scores[i][2] += 1;
                } else if (apartments.get(i).getTag3().equals(tagName)) {
                    scores[i][3] += 1;
                }
            }
        }
        for (int i = 0; i < apartments.size(); i++) {
            scores[i][4] = (scores[i][0]+scores[i][1]+scores[i][2]+scores[i][3])/4;
        }
        for (int i = 0; i < apartments.size() - 1; i++) {
            for (int j = 1; j < apartments.size() - i; j++) {
                if (scores[j][4]>scores[j-1][4]) { // 比较两个浮点数的大小
                    //交换权值
                    float s = scores[j][4];
                    scores[j][4] = scores[j-1][4];
                    scores[j-1][4] = s;
                    //交换列表对象
                    Apartment a;
                    a = apartments.get(j - 1);
                    apartments.set((j - 1), apartments.get(j));
                    apartments.set(j, a);
                    for(Apartment ap:apartments){
                        System.out.println(ap.getApartName());
                    }
                }
            }
        }
        List<Apartment> retApartments = apartments.subList(0,5);
        return retApartments;
    }

    /**
     * 导出部门信息
     */
    @Override
    public void export(OutputStream os) {
        //获取要导出的数据列表
        List<Apartment> list = apartmentMapper.getAllApartment();
        //创建一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        String sheetName = "部门信息";

        //创建一个工作表
        HSSFSheet sheet = wb.createSheet(sheetName);
        //创建一行,行的索引是从0开始, 写标题
        HSSFRow row = sheet.createRow(0);
        String[] header = {"编号", "科室名称"};
        int[] width = {5000, 8000};
        HSSFCell cell = null;
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
            //设置列宽
            sheet.setColumnWidth(i, width[i]);
        }
        //导出的内容
        int rowCount = 1;
        for (Apartment apartment : list) {
            row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(apartment.getApartId());//id
            row.createCell(1).setCellValue(apartment.getApartName());//名称
        }
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导入数据
     */
    @Override
    public void doImport(InputStream is) {
        HSSFWorkbook wb = null;
        try {
            try {
                wb = new HSSFWorkbook(is);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            HSSFSheet sheet = wb.getSheetAt(0);
            //读取数据
            //最后一行的行号
            int lastRow = sheet.getLastRowNum();
            Apartment apartment = null;
            for (int i = 1; i <= lastRow; i++) {
                apartment = new Apartment();
                //判断是否已经存在，通过ID来判断
                Apartment apartment1 = apartmentMapper.getApartementById(sheet.getRow(i).getCell(0).getStringCellValue());
                if (apartment1 == null) {
                    apartment.setApartId(sheet.getRow(i).getCell(0).getStringCellValue());
                    apartment.setApartName(sheet.getRow(i).getCell(1).getStringCellValue());
                    apartmentMapper.addApartment(apartment);
                }
            }
        } finally {
            if (null != wb) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
