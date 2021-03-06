package com.demo.app.product.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonPropertyOrder({"id","dni","balance","currency","accountNumber","cvc","createdAt","updateAt"})
@Document(collection = "fixed_term_account")
@Data
public class FixedTermAccount extends Audit{
    @Id
    private String id;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private TypeCurrency currency;

    @Field(name = "account_number")
    @Size(min = 16,max = 16)
    private String accountNumber;

    @Range(min = 100,max = 999)
    private Integer cvc;

    @NotEmpty
    @Size(min = 8,max = 11)
    private String identifier;

    @Field(name = "number_transactions")
    private Integer numberTransactions = 1;
}
