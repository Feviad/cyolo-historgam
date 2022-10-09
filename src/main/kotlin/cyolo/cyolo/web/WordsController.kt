package cyolo.cyolo.web

import cyolo.cyolo.service.WordsProcessor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/words")
class WordsController(
    private val processor: WordsProcessor
) {


    @PostMapping
    suspend fun words(
        @RequestBody input: String
    ): ResponseEntity<Any> {
        processor.parse(input)
        return ResponseEntity(HttpStatus.OK)
    }

    //any data?
    @GetMapping
    suspend fun rank(): Map<String, Int> {
        return processor.getRank()
    }


}
