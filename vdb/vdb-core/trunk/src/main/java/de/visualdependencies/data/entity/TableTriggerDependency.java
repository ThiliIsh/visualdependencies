package de.visualdependencies.data.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
@Table(name = "table_trigger_dependencies")
public class TableTriggerDependency extends AbstractEntity<Long> {

	private static final long serialVersionUID = -21971115593825263L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Schema schema;

	@OneToOne
	@JoinColumn
	private SchemaTable table;

	@OneToOne
	@JoinColumn
	private SchemaTrigger trigger;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "table_trigger_dependencies_data", joinColumns = @JoinColumn(name = "dependencyId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	public Map<String, String> getData() {
		if (data == null) {
			setData(new HashMap<String, String>());
		}
		return data;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Schema getSchema() {
		return schema;
	}

	public SchemaTable getTable() {
		return table;
	}

	public SchemaTrigger getTrigger() {
		return trigger;
	}

	public void setData(final Map<String, String> data) {
		this.data = data;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSchema(final Schema schema) {
		this.schema = schema;
	}

	public void setTable(final SchemaTable table) {
		this.table = table;
	}

	public void setTrigger(final SchemaTrigger trigger) {
		this.trigger = trigger;
	}

}
