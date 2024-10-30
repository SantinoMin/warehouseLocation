package warehouseLocation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ParentCategory {

    @Id
    private Long id;

    private String name; // 대분류 이름 추가 가능

    @OneToMany(mappedBy = "parent")
    private List<ChildCategory> children; // 자식 카테고리 목록

    // Getters and Setters
}