package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class SectionAddressDistrictView {

    private long sectionId;
    private String sectionName;
    private String sectionDescription;
    private long sectionCapacity;

    private long addressId;
    private String houseNumber;
    private String street;
    private String postalCode;
    private long districtId;
    private String districtName;

    public static RowMapper<SectionAddressDistrictView> getRowMapper() {
        return (rs, rowNum) -> {
            SectionAddressDistrictView view = new SectionAddressDistrictView();

            view.setSectionId(rs.getLong("SECTIONID"));
            view.setSectionName(rs.getString("SECTION_NAME"));
            view.setSectionDescription(rs.getString("SECTION_DESCRIPTION"));
            view.setSectionCapacity(rs.getLong("SECTION_CAPACITY"));

            view.setAddressId(rs.getLong("ADDRESSID"));
            view.setHouseNumber(rs.getString("HOUSENUMBER"));
            view.setStreet(rs.getString("STREET"));
            view.setPostalCode(rs.getString("POSTALCODE"));
            view.setDistrictId(rs.getLong("DISTRICTID"));
            view.setDistrictName(rs.getString("DISTRICT_NAME"));

            return view;
        };
    }
}
