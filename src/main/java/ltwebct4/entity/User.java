package ltwebct4.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
@Entity 
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserId")
    private int userId;

    @Column(name="Username", columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name="Password", columnDefinition = "NVARCHAR(50)")
    private String password;

    @Column(name="Fullname", columnDefinition = "NVARCHAR(50)")
    private String fullname;

    @Column(name="Email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name="RoleId")
    private int roleid; // 1: User, 2: Manager, 3: Admin

    @Column(name="Phone", columnDefinition="VARCHAR(20)")
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;
}