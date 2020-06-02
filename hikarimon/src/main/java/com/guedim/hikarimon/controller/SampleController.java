package com.guedim.hikarimon.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SampleController {

	private final DataSource dataSource;

	public SampleController(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@RequestMapping("/commit")
	public List<Map<String, String>> select() {
		List<Map<String, String>> results = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM INFORMATION_SCHEMA.COLUMNS")) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, String> result = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i + 1);
					result.put(columnName, resultSet.getString(columnName));
				}
				results.add(result);
			}
			connection.commit();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return results;
	}

	@RequestMapping("/rollback")
	public List<Map<String, String>> rollback() {
		List<Map<String, String>> results = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM INFORMATION_SCHEMA.COLUMNS")) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, String> result = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i + 1);
					result.put(columnName, resultSet.getString(columnName));
				}
				results.add(result);
			}
			connection.rollback();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return results;
	}

	@RequestMapping("/query-error")
	public void error() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			statement.execute("SELECT UNDEFINED()");
		} catch (Exception ignored) {
		}
	}

}
