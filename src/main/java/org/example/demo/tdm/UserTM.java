package org.example.demo.tdm;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserTM {
    private String user_id;
    private String username;
    private String password;
    private String role;
}
