package com.frame.BeanVo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by wuming on 2019/7/30.
 */
@Data
@Document(collection = "user")
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String _id;
    private String name;//名称
    private Integer age;//年龄
    private String occupation;//职业

    public UserDto() {
    }

    public UserDto (String name, Integer age, String occupation) {
        super ();
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }
}
