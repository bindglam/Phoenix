package com.bindglam.phoenix.item.builder

data class LoreFormat(val tokens: List<String>) {
    fun process(applier: (String) -> String?): List<String> {
        val result = arrayListOf<String>()

        token@ for(token in tokens) {
            val resultToken = StringBuilder() // 처리된 토큰

            var placeholderStarted = false
            val placeholder = StringBuilder()

            val isRequired = token.startsWith("(required)")
            val startIdx = if(!isRequired) 0 else "(required)".length

            for(i in startIdx..<token.length) {
                val char = token[i]

                if(char == '[') {
                    placeholderStarted = true
                    continue
                }

                if(placeholderStarted) { // 플레이스홀더 부분에 진입하였는가?
                    if(char == ']') { // 플레이스홀더 끝부분인가?
                        placeholderStarted = false

                        val appliedPlaceholder = applier(placeholder.toString())
                        if(appliedPlaceholder == null) {
                            resultToken.append(if(isRequired) "" else continue@token)
                        } else {
                            resultToken.append(appliedPlaceholder) // 대체된 텍스트로 변경
                        }

                        placeholder.clear()
                        continue
                    }

                    placeholder.append(char)
                } else {
                    resultToken.append(char)
                }
            }

            result.add(resultToken.toString())
        }

        return result
    }
}