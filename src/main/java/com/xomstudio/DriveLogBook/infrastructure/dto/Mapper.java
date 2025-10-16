package com.xomstudio.DriveLogBook.infrastructure.dto;

public interface Mapper<A, B> {

    B mapFromEntityToDTO(A a);

    A mapFromDTOToEntity(B b);

}