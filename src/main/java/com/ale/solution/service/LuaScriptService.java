package com.ale.solution.service;

import java.util.List;
import java.util.Map;

public interface LuaScriptService {
    /**
     * List设置lua的KEYS,用Map设置Lua的ARGV[1]
     */
    Boolean redisAddScriptExec(List<String> keyList, Map<String, Object> argvMap);
}
