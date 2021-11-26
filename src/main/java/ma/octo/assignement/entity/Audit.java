package ma.octo.assignement.entity;

import javax.persistence.*;

import ma.octo.assignement.entity.util.EventType;


@Entity
@Table(name = "AUDIT")
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100)
	private String message;

	@Enumerated(EnumType.STRING)
	private EventType eventType;

	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

}
