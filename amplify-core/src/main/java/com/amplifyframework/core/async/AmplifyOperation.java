/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amplifyframework.core.async;

import com.amplifyframework.core.category.CategoryType;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * An abstract representation of an Amplify unit of work. Subclasses may aggregate multiple work items
 * to fulfill a single "AmplifyOperation", such as an "extract text operation" which might include
 * uploading an image to cloud storage, processing it via a Predictions engine, and translating the results.
 *
 * AmplifyOperations are used by plugin developers to perform tasks on behalf of the calling app. They have a default
 * implementation of a `publish` method that sends a contextualized payload to the Hub.
 *
 * Pausable/resumable tasks that do not require Hub dispatching should use {@link AsyncOperation} instead.
 */
public abstract class AmplifyOperation
        <R extends AmplifyOperationRequest<?>>
        implements AsyncOperation {

    // Incoming parameters of the original request. The Request will be included as part of the
    // events being emitted by this operation. The request gives context about the operation that
    // emitted the events.
    private final R request;

    // The unique ID of the operation. In categories where operations are persisted for future
    // processing, this id can be used to identify previously-scheduled work for progress tracking
    // or other functions.
    private final UUID operationId;

    // Required by Hub to find the HubChannel mapped to the
    // CategoryType.
    private final CategoryType categoryType;

    /**
     * Constructor.
     *
     * @param request request object encapsulates the parameters of the operation
     */
    public AmplifyOperation(@NonNull final CategoryType categoryType,
                            @NonNull final R request) {
        this(categoryType, request, null);
    }

    /**
     * Constructor.
     *
     * @param request request object encapsulates the parameters of the operation
     * @param eventListener listener that is attached to the operation. The listener
     *                      will be notified of the events generated by this operation
     */
    public AmplifyOperation(@NonNull final CategoryType categoryType,
                            @NonNull final R request,
                            @Nullable final EventListener<?> eventListener) {
        this.categoryType = categoryType;
        this.request = request;
        this.operationId = UUID.randomUUID();
    }

    @Override
    public void start() {

    }

    public R getRequest() {
        return request;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}