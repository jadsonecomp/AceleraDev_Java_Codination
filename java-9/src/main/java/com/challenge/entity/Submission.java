package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Submission {

    @EmbeddedId
    private SubmissionEntity submissionEntity;

    @NotNull
    private double score;

    @CreatedDate
    private Timestamp createdat;


    public SubmissionEntity getSubmissionEntity() {
        return submissionEntity;
    }

    public void setSubmissionEntity(SubmissionEntity submissionEntity) {
        this.submissionEntity = submissionEntity;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

}
