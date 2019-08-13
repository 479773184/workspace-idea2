package com.jk.dao;

import java.util.List;
import java.util.Map;

public interface BookDao {
    List<Map<String, Object>> queryBook();

    List<Map<String, Object>> queryMM();

    List<Map<String, Object>> queryZhu();
}