package com.netflixclone.backend.service;

import com.netflixclone.backend.model.SubscriptionPlan;
import com.netflixclone.backend.model.User;
import com.netflixclone.backend.repository.SubscriptionPlanRepository;
import com.netflixclone.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository planRepository;
    private final UserRepository userRepository;

    public List<SubscriptionPlan> getAllPlans() {
        return planRepository.findByIsActive(true);
    }

    public Optional<SubscriptionPlan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    public SubscriptionPlan createPlan(SubscriptionPlan plan) {
        plan.setIsActive(true);
        return planRepository.save(plan);
    }

    public SubscriptionPlan updatePlan(Long id, SubscriptionPlan planDetails) {
        Optional<SubscriptionPlan> existingPlan = planRepository.findById(id);
        if (existingPlan.isPresent()) {
            SubscriptionPlan plan = existingPlan.get();
            plan.setName(planDetails.getName());
            plan.setPrice(planDetails.getPrice());
            plan.setDescription(planDetails.getDescription());
            plan.setFeatures(planDetails.getFeatures());
            plan.setMaxScreens(planDetails.getMaxScreens());
            plan.setVideoQuality(planDetails.getVideoQuality());
            return planRepository.save(plan);
        }
        return null;
    }

    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    public User assignPlanToUser(Long userId, Long planId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<SubscriptionPlan> planOpt = planRepository.findById(planId);

        if (userOpt.isPresent() && planOpt.isPresent()) {
            User user = userOpt.get();
            user.setPlan(planOpt.get());
            return userRepository.save(user);
        }
        return null;
    }
}
