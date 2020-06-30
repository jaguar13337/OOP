package ru.nsu.fit.g18214.yakovlev.dsl.engine

import org.codehaus.groovy.control.CompilerConfiguration

class Engine {
    static def executeDSL(String path) throws FileNotFoundException {
        Config dsl
        def compileConfig = new CompilerConfiguration()
        compileConfig.scriptBaseClass = 'ru.nsu.fit.g18214.yakovlev.dsl.language.Lang'
        def shell = new GroovyShell(compileConfig)
        File file = new File(path)
        dsl = shell.evaluate(file) as Config
        dsl
    }
}
