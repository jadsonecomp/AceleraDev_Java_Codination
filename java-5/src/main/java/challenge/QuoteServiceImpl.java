package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository quoteRepository;

	public QuoteServiceImpl(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	private Quote findRandomByQuoteList(List<Quote> quoteList){
		List<Quote> findResult = quoteList;
		return findResult.get(new Random().nextInt(findResult.size()));
	}

	@Override
	public Quote getQuote() {
		return findRandomByQuoteList(quoteRepository.findAll());
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return findRandomByQuoteList(quoteRepository.findByActor(actor));
	}

}
