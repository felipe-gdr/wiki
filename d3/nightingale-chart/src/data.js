export const sampleData = {
  expectations: [
    {
      name: "Master the Craft",
      highLevels: [
        {
          name: "Value your craft",
          specifics: [
            {
              name: "Understanding of environment",
              value: 3,
            },
            {
              name: "Technical proficiency",
              value: 2,
            },
            {
              name: "Scale of assignments",
              value: 3,
            },
            {
              name: "Architectural contributions",
              value: 3,
            },
            {
              name: "Contributes to bug fixing",
              value: 2,
            },
            {
              name: "Contributes to code reviews",
              value: 3,
            },
            {
              name: "Contributes to documentation",
              value: 2,
            },
            {
              name: "Demonstrates operational excellence",
              value: 3,
            },
          ],
        },
        {
          name: "Produce simple, effective solutions",
          specifics: [
            {
              name: "Delivers with security in mind",
              value: 2,
            },
            {
              name: "Code quality",
              value: 2,
            },
            {
              name: "Designing solutions",
              value: 2,
            },
            {
              name: "Demonstrates judgment when determining appropriate action",
              value: 2,
            },
            {
              name: "Instrumentation and troubleshooting",
              value: 2,
            },
          ],
        },
        {
          name: "Innovate",
          specifics: [
            {
              name: "Is a change agent and innovator",
              value: 3,
            },
            {
              name: "Takes initiative",
              value: 2,
            },
          ],
        },
      ],
    },
    {
      name: "Drive outcomes",
      highLevels: [
        {
          name: "Deliver on schedule",
          specifics: [
            {
              name: "Scoping, estimation, delivery",
              value: 2,
            },
            {
              name: "Executes incrementally",
              value: 3,
            },
          ],
        },
        {
          name: "Collaborate",
          specifics: [
            {
              name: "Participates in the incident management process",
              value: 2,
            },
            {
              name: "Communicates progress and expectations",
              value: 3,
            },
          ],
        },
        {
          name: "Do the right thing",
          specifics: [
            {
              name: "Delivers quality, leaves things better than they found it",
              value: 2,
            },
            {
              name: "Helps with oncall duties",
              value: 1,
            },
            {
              name: "Anticipates and avoids blockers",
              value: 3,
            },
          ],
        },
      ],
    },
    {
      name: "Lead & inspire",
      highLevels: [
        {
          name: "Help the team win",
          specifics: [
            {
              name: "Is a team player",
              value: 3,
            },
            {
              name: "Lives Atlassian values",
              value: 1,
            },
            {
              name: "Can do attitude ",
              value: 3,
            },
            {
              name: "Identifies talent / role in interview process",
              value: 2,
            },
          ],
        },
        {
          name: "Continuously improve",
          specifics: [
            {
              name: "Drives improvement and shares knowledge",
              // value: 3,
              value: 4,
            },
            {
              name: "Fosters a culture of continuous learning and improvement",
              // value: 2,
              value: 4,
            },
          ],
        },
        {
          name: "Consider diverse perspectives",
          specifics: [
            {
              name: "Seeks and respond to feedback",
              value: null,
            },
            {
              name: "Productive discussion",
              value: 2,
            },
            {
              name: "Healthy interpersonal problem solving",
              value: 2,
            },
          ],
        },
        {
          name: "Influence",
          specifics: [
            {
              name: "Influences technical decisions",
              value: 2,
            },
            {
              name: "Mentoring",
              value: 3,
            },
            {
              name: "Technical thought leadership",
              value: null,
            },
            {
              name: "Guide a team from concept to deliverable",
              value: 3,
            },
          ],
        },
      ],
    },
    {
      name: "Have customer impact",
      highLevels: [
        {
          name: "Puts customer first",
          specifics: [
            {
              name: "Builds trust with customers",
              value: 2,
            },
            {
              name: "Creates value for customers",
              value: 2,
            },
          ],
        },
        {
          name: "Think about business metrics",
          specifics: [
            {
              name: "Uses data to drive actions and set goals",
              value: 2,
            },
            {
              name: "Awareness of goals, risks, costs and constraints",
              value: 2,
            },
          ],
        },
        {
          name: "Think about how team goals align with Atlassian strategy",
          specifics: [
            {
              name: "Works across boundaries to achieve goals",
              value: 2,
            },
            {
              name: "Has a sharing understanding of the strategy",
              value: 2,
            },
          ],
        },
      ],
    },
  ],
};

export const maxedOutData = {
  expectations: sampleData.expectations.map((expectation) => ({
    ...expectation,
    highLevels: expectation.highLevels.map((highLevel) => ({
      ...highLevel,
      specifics: highLevel.specifics.map((specific) => ({ ...specific, value: 4 })),
    })),
  })),
};
