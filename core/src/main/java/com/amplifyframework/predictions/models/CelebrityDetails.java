/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.util.Immutable;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Detailed metadata on a celebrity detection result.
 */
public final class CelebrityDetails {
    private final Celebrity celebrity;
    private final Pose pose;
    private final List<FacialFeature> facialFeatures;
    private final List<URL> urls;

    private CelebrityDetails(final Builder builder) {
        this.celebrity = builder.getCelebrity();
        this.pose = builder.getPose();
        this.facialFeatures = builder.getfacialFeatures();
        this.urls = builder.getUrls();
    }

    /**
     * Gets the celebrity.
     * @return the celebrity
     */
    @NonNull
    public Celebrity getCelebrity() {
        return celebrity;
    }

    /**
     * Gets the detected pose.
     * @return the pose
     */
    @Nullable
    public Pose getPose() {
        return pose;
    }

    /**
     * Gets the detected face details of the celebrity.
     * @return the facial features
     */
    @NonNull
    public List<FacialFeature> getfacialFeatures() {
        return Immutable.of(facialFeatures);
    }

    /**
     * Gets the list of URLs that contain
     * more information about the celebrity.
     * @return the urls
     */
    @NonNull
    public List<URL> getUrls() {
        return Immutable.of(urls);
    }

    /**
     * Gets the builder to help easily construct the
     * metadata object.
     * @return an unassigned builder instance
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link CelebrityDetails}.
     */
    public static final class Builder {
        private Celebrity celebrity;
        private Pose pose;
        private List<FacialFeature> facialFeatures;
        private List<URL> urls;

        private Builder() {
            this.facialFeatures = Collections.emptyList();
            this.urls = Collections.emptyList();
        }

        /**
         * Sets the celebrity and return this builder.
         * @param celebrity the celebrity's name
         * @return this builder instance
         */
        @NonNull
        public Builder celebrity(@NonNull Celebrity celebrity) {
            this.celebrity = Objects.requireNonNull(celebrity);
            return this;
        }

        /**
         * Sets the pose and return this builder.
         * @param pose the pose
         * @return this builder instance
         */
        @NonNull
        public Builder pose(@Nullable Pose pose) {
            this.pose = pose;
            return this;
        }

        /**
         * Sets the facial features and return this builder.
         * @param facialFeatures the celebrity facial facial features
         * @return this builder instance
         */
        @NonNull
        public Builder facialFeatures(@NonNull List<FacialFeature> facialFeatures) {
            this.facialFeatures = Objects.requireNonNull(facialFeatures);
            return this;
        }

        /**
         * Sets the urls and return this builder.
         * @param urls the urls
         * @return this builder instance
         */
        @NonNull
        public Builder urls(@NonNull List<URL> urls) {
            this.urls = Objects.requireNonNull(urls);
            return this;
        }

        /**
         * Create a new instance of {@link CelebrityDetails} using
         * the values assigned to this builder instance.
         * @return An instance of {@link CelebrityDetails}
         */
        @NonNull
        public CelebrityDetails build() {
            return new CelebrityDetails(this);
        }

        @NonNull
        Celebrity getCelebrity() {
            return Objects.requireNonNull(celebrity);
        }

        @Nullable
        Pose getPose() {
            return pose;
        }

        @NonNull
        List<FacialFeature> getfacialFeatures() {
            return Objects.requireNonNull(facialFeatures);
        }

        @NonNull
        List<URL> getUrls() {
            return Objects.requireNonNull(urls);
        }
    }
}