package ru.nsu.fit.g18214.yakovlev.dsl.engine

import org.codehaus.groovy.control.CompilerConfiguration

class EngineDSL {
    static def executeDSL(String path) throws FileNotFoundException {
        ConfigDSL dsl
        def compileConfig = new CompilerConfiguration()
        compileConfig.scriptBaseClass = 'ru.nsu.fit.g18214.yakovlev.dsl.language.LangDSL'
        def shell = new GroovyShell(compileConfig)
        File file = new File(path)
        dsl = shell.evaluate(file) as ConfigDSL
        dsl


    }
}
