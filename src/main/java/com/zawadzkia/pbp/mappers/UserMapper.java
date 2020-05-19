package com.zawadzkia.pbp.mappers;

import com.zawadzkia.pbp.model.User;
import com.zawadzkia.pbp.model.mt.UserMT;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    User converUserMT(UserMT userMT);

}
