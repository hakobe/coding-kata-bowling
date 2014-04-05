package Bowling::Game;
use v5.14;
use warnings;

sub new {
    my ($class) = @_;

    my $self = bless {
        score => 0,
    }, $class;

    return $self;
}

sub role {
    my ($self, $score) = @_;
    $self->{score} += $score;
}

sub score {
    my ($self) = @_;
    return $self->{score};
}

1;
