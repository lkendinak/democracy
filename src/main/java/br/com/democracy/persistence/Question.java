package br.com.democracy.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "QUESTION")
public class Question extends BaseEntity {

	private static final long serialVersionUID = 3260774000214528827L;

	@Column(name = "QUESTION", nullable = false)
	private String question;

	/** QuestionStatusEnum */
	@Column(name = "STATUS", nullable = false)
	private Integer status;
	
	/** QuestionTypeEnum */
	@Column(name = "TYPE", nullable = false)
	private Integer type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ACTIVATED", nullable = true)
	private Date dateActivated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<DiscursiveAnswer> discursiveAnswers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.question", cascade=CascadeType.ALL)
	private Set<UserQuestion> userQuestions = new HashSet<UserQuestion>(0);

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<DiscursiveAnswer> getDiscursiveAnswers() {
		return discursiveAnswers;
	}

	public void setDiscursiveAnswers(List<DiscursiveAnswer> discursiveAnswers) {
		this.discursiveAnswers = discursiveAnswers;
	}

	public Set<UserQuestion> getUserQuestions() {
		return userQuestions;
	}

	public void setUserQuestions(Set<UserQuestion> userQuestions) {
		this.userQuestions = userQuestions;
	}

}
