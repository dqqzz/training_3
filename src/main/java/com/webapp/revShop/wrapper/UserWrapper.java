package com.webapp.revShop.wrapper;

import lombok.Data;

@Data
public class UserWrapper {

    private Integer user_id;

    private String user_name;

    private String user_email;

    private String user_role;

    private String user_status;

    public UserWrapper() {
    }

    public UserWrapper(Integer user_id, String user_name, String user_email, String user_role, String user_status) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_role = user_role;
        this.user_status = user_status;
    }
}
