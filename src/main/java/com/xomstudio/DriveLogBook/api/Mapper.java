package com.xomstudio.DriveLogBook.api;

public interface Mapper<A, B> {

    B mapFromEntityToDTO(A a);

    A mapFromDTOToEntity(B b);

}