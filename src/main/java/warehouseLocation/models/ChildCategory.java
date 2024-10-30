package warehouseLocation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChildCategory {

    @Id
    private Long id;

    private String name; // 중분류 이름 추가 가능

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentCategory parent; // 부모 카테고리 참조

    // Getters and Setters
}