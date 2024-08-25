package scheduler;

import com.digest.journalApp.cache.AppCache;
import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.entity.User;
import com.digest.journalApp.enums.Sentiment;
import com.digest.journalApp.repository.UserRepoImpl;
import com.digest.journalApp.service.EmailService;
import com.digest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    // Uncomment for weekly schedule
    // @Scheduled(cron = "0 0 9 * * SUN")
    @Scheduled(cron = "0 * * ? * *")  // Run every minute for testing
    public void sendWeeklySentimentEmail() {
        List<User> users = userRepo.getUserForSA();

        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntry();

            List<Sentiment> recentSentiments = journalEntries.stream()
                    .filter(entry -> entry.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)
                    .collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : recentSentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = sentimentCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);

            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment of Last 7 Days", mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void clearAppCache() {
        appCache.init();
    }
}
